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
import org.apache.geode.cache.client.ServerOperationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfigureGeode.class})
public class Tester {
    @Resource
    private Region testRegion;

    @Test(expected = ServerOperationException.class)
    public void insert100() {
        int counter = 0;
        while (counter < 100) {
            SampleData sampleData = new SampleData(counter++, new Date(), "some text" + counter * 2, counter % 2 == 0);
            testRegion.put(counter, sampleData);
        }
        System.out.println("testRegion.keySetOnServer().size() = " + testRegion.keySetOnServer().size());
    }
}
