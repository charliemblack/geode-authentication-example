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

import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.security.AuthInitialize;
import org.apache.geode.security.AuthenticationFailedException;

import java.util.Properties;

public class ClientAuthInitialize implements AuthInitialize {

    public static final String USER_NAME = "security-username";
    public static final String PASSWORD = "security-password";


    public static AuthInitialize create() {
        return new ClientAuthInitialize();
    }


    @Override
    public Properties getCredentials(final Properties securityProperties,
                                     final DistributedMember server, final boolean isPeer) throws AuthenticationFailedException {
        String userName = securityProperties.getProperty(USER_NAME);
        if (userName == null) {
            throw new AuthenticationFailedException(
                    "UserPasswordAuthInit: user name property [" + USER_NAME + "] not set.");
        }

        String password = securityProperties.getProperty(PASSWORD);
        if (password == null) {
            password = "";
        }

        Properties securityPropertiesCopy = new Properties();
        securityPropertiesCopy.setProperty(USER_NAME, userName);
        securityPropertiesCopy.setProperty(PASSWORD, password);
        return securityPropertiesCopy;
    }
}
