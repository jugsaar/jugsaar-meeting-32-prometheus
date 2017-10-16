#!/bin/bash

DEMO_HOME=$PWD/demos/10-simple-demo

start(){

    echo \\\\\ start node_exporter...
    bin/node_exporter \
      --collector.systemd \
      &

    sleep 2

    echo \\\\\ start prometheus...
    bin/prometheus-1.8.0.linux-amd64/prometheus \
      -storage.local.path=$DEMO_HOME/prometheus/data \
      -config.file=$DEMO_HOME/prometheus/prometheus.yml \
      &

    sleep 2

    echo \\\\\ start grafana_server...
    bin/grafana-4.5.2/bin/grafana-server \
      -homepath $PWD/bin/grafana-4.5.2 \
      web \
      &
}

stop() {
    pkill prometheus

    pkill node_exporter

    pkill grafana-server
}

case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  restart)
    stop
    start
    ;;
  *)
    echo "Usage: $0 {start|stop|restart}" >&2
    exit 1
    ;;
esac