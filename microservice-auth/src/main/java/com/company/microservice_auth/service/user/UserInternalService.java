package com.company.microservice_auth.service.user;

import com.company.microservice_auth.entity.User;
import com.company.microservice_auth.service.CommonInternalService;
import org.yaml.snakeyaml.events.Event;

public interface UserInternalService extends CommonInternalService<User, Long> {
}
