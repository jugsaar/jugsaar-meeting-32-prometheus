#!/bin/bash

# Download prometheus
wget -P files https://github.com/prometheus/prometheus/releases/download/v1.8.0/prometheus-1.8.0.linux-amd64.tar.gz

# Download alertmanager
wget -P files https://github.com/prometheus/alertmanager/releases/download/v0.9.1/alertmanager-0.9.1.linux-amd64.tar.gz

# Download node_exporter
wget -P files https://github.com/prometheus/node_exporter/releases/download/v0.15.0/node_exporter-0.15.0.linux-amd64.tar.gz

# Download pushgateway
wget -P files https://github.com/prometheus/pushgateway/releases/download/v0.4.0/pushgateway-0.4.0.linux-amd64.tar.gz

# Download postgres_exporter
wget -P files https://github.com/wrouesnel/postgres_exporter/releases/download/v0.2.3/postgres_exporter

# Download blackbox_exporter
wget -P files https://github.com/prometheus/blackbox_exporter/releases/download/v0.10.0/blackbox_exporter-0.10.0.linux-amd64.tar.gz

# Download grafana
wget -P files https://s3-us-west-2.amazonaws.com/grafana-releases/release/grafana-4.5.2.linux-x64.tar.gz 