package com.example.ordermessage.order.api;

import com.example.ordermessage.order.domain.Order;
import com.example.ordermessage.order.domain.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderFacade.class)
class OrderFacadeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void shouldReturnOkAfterProcessingRequest() throws Exception {
        //given
        OrderDto expectedOrder = new OrderDto("test", "123");
        ResponseEntity<String> resp = new ResponseEntity<>(HttpStatus.OK);
        given(restTemplate.getForEntity("http://localhost:8085/v1/orders", String.class)).willReturn(resp);
        Order order = new Order(expectedOrder.getItem(), expectedOrder.getCount());
        doNothing().when(orderService).sendOrder(order);

        String request = """
                {
                "item":"test",
                "count":"123"
                }
                """;

        //when
        mockMvc.perform(post("/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestForInvalidInput() throws Exception {
        //given
        String request = """
                {
                "item":"test"
                }
                """;

        //when
        mockMvc.perform(post("/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(request))
                .andExpect(status().isBadRequest());
    }
}