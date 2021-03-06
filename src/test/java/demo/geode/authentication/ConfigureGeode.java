/*
 * Copyright 2017 Charlie Black
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package demo.geode.authentication;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureGeode {
    @Bean
    public ClientCache clientCache() {
        ClientCacheFactory factory = new ClientCacheFactory();
        factory.set("security-client-auth-init", "demo.geode.authentication.ClientAuthInitialize.create");
        factory.set("security-username", "failuser");
        factory.set("security-password", "1234567");
        factory.set("log-level", "error");
        factory.addPoolLocator("voltron", 10335);
        factory.setPdxSerializer(new ReflectionBasedAutoSerializer("demo.geode.authentication.*"));
        return factory.create();
    }

    @Bean
    public Region<Integer, SampleData> testRegion(ClientCache clientCache) {
        return clientCache.<Integer, SampleData>createClientRegionFactory(ClientRegionShortcut.PROXY).create("test");
    }
}
