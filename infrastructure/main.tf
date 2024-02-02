terraform {
  backend "s3" {
    bucket = "domi-devopis-terraform-state"
    key    = "terraform/backend"
    region = "eu-west-1"
  }
}

locals {
  env_name         = "sandbox"
  aws_region       = "ewu-west-1"
  k8s_cluster_name = "ms-cluster"
}