#!/usr/bin/env bash

printf '\n-----> INVOKING LAMBDA\n\n'

aws lambda invoke \
--region eu-west-1 \
--function-name alexa-dispute-resolver \
--payload "$(jq -r . test.json | tr -d " \t\n\r")" \
output.txt

printf '\n-----> LAMBDA OUTPUT\n\n'

cat output.txt
rm output.txt

printf '\n\n'