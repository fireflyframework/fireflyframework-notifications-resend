/*
 * Copyright 2024-2026 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.fireflyframework.notifications.providers.resend.config.v1;

import org.fireflyframework.client.RestClient;
import org.fireflyframework.client.ServiceClient;
import org.fireflyframework.notifications.providers.resend.properties.v1.ResendProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "notifications.email", name = "provider", havingValue = "resend")
public class ResendConfig {

    @Bean
    @ConditionalOnProperty(prefix = "resend", name = "api-key")
    public RestClient resendRestClient(ResendProperties properties) {
        log.info("Initializing Resend email provider with base URL: {}", properties.getBaseUrl());
        return ServiceClient.rest("resend")
                .baseUrl(properties.getBaseUrl())
                .jsonContentType()
                .defaultHeader("Authorization", "Bearer " + properties.getApiKey())
                .build();
    }
}
