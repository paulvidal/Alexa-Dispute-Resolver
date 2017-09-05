#!/usr/bin/env bash

printf '\n-----> GENERATING NEW JAR\n\n'

./gradlew shadowJar

printf '\n-----> UPDATING LAMBDA\n\n'

aws lambda update-function-code \
--region eu-west-1 \
--function-name alexa-dispute-resolver \
--zip-file fileb://build/libs/Alexa-Dispute-Resolver-all.jar