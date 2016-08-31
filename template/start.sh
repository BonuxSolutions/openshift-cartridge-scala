#!/bin/bash

SN="the-code"
DIR=`pwd`

rm -f frontend/target/universal/stage/RUNNING_PID

if sbt compile; then
    tmux new-session -s "${SN}" -n ${SN} -d
    tmux set -t "$SN" set-remain-on-exit on

    tmux new-window -t "$SN:1" -n "backend" "sbt 'project backend' 'run'"
    tmux new-window -t "$SN:2" -n "frontend" "sbt 'project frontend' 'start' -Dhttp.port=9090 -Dakka.remote.netty.tcp.port=0"

    tmux attach -t ${SN}
fi
