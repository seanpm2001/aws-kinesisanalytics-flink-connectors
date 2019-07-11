/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazonaws.services.kinesisanalytics.flink.connectors.provider.credential;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.kinesisanalytics.flink.connectors.config.AWSConfigConstants;
import com.amazonaws.services.kinesisanalytics.flink.connectors.util.AWSUtil;

import java.util.Properties;

public class ProfileCredentialProvider extends CredentialProvider {


    public ProfileCredentialProvider(Properties properties) {
        super(AWSUtil.validateProfileProviderConfiguration(properties));
    }

    @Override
    public AWSCredentialsProvider getAwsCredentialsProvider() {
        final String profileName = getProperties().getProperty(AWSConfigConstants.AWS_PROFILE_NAME);
        final String profilePath = getProperties().getProperty(AWSConfigConstants.AWS_PROFILE_PATH, null);

        return (profilePath == null) ? new ProfileCredentialsProvider(profileName) :
            new ProfileCredentialsProvider(profileName, profilePath);
    }
}
