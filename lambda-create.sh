#!/usr/bin/env bash

printf '\n-----> GENERATING JAR\n'

./gradlew shadowJar

printf '\n-----> CREATING LAMBDA\n\n'

aws lambda create-function \
--region us-east-1 \
--function-name alexa-dispute-resolver \
--zip-file fileb://build/libs/Alexa-Dispute-Resolver-all.jar \
--role arn:aws:iam::582397839375:role/lambda_basic_execution \
--handler AppRequestHandler \
--runtime java8 \
--timeout 15 \
--memory-size 128