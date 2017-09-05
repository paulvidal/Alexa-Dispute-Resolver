#!/usr/bin/env bash

printf '\n-----> UPDATING LAMBDA CONFIG\n\n'

aws lambda update-function-configuration \
--region eu-west-1 \
--function-name alexa-dispute-resolver \
--handler AppRequestHandler
