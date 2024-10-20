import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_JARS;
import static com.tngtech.archunit.core.importer.ImportOption.Predefined.DO_NOT_INCLUDE_TESTS;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class KafkaProducerArchTest {
    private static final String ORDER_DOMAIN_CONTEXT = "com.example.ordermessage.order.domain";

    @Test
    void orderDomainShouldBeIndependent() {
        classes().that()
                .resideInAPackage(ORDER_DOMAIN_CONTEXT)
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage(
                        javaPackages(), jpaPackages(), springFrameworkPackages(),
                        ORDER_DOMAIN_CONTEXT)
                .check(projectManagementClasses());
    }

    private String javaPackages() {
        return allPackagesIn("java");
    }

    private String jpaPackages() {
        return allPackagesIn("javax.persistence");
    }

    private String springFrameworkPackages() {
        return allPackagesIn("org.springframework");
    }

    private static String allPackagesIn(String packageName) {
        return packageName + "..";
    }

    public static JavaClasses projectManagementClasses() {
        return classesFrom(ORDER_DOMAIN_CONTEXT);
    }

    private static JavaClasses classesFrom(String packageName) {
        return new ClassFileImporter()
                .withImportOption(DO_NOT_INCLUDE_JARS)
                .withImportOption(DO_NOT_INCLUDE_TESTS)
                .importPackages(packageName);
    }
}
