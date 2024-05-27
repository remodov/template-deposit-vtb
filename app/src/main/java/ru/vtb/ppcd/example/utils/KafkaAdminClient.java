package ru.vtb.ppcd.example.utils;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Properties;

@Component
public class KafkaAdminClient {

    @Value("${kafka.default-url}")
    private String defaultUrl;

    public boolean verifyConnection() {
        Properties props =new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, defaultUrl);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
        props.put(AdminClientConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 5000);
        props.put(AdminClientConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, 5000);

        Collection<Node> nodes;
        try (AdminClient client = AdminClient.create(props)) {
            nodes = client.describeCluster().nodes().get();
        } catch (Exception e) {
            return false;
        }

        return !nodes.isEmpty();
    }
}
