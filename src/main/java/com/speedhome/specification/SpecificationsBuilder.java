package com.speedhome.specification;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class SpecificationsBuilder<T> {

    protected List<SearchCriteria> criteriaList = Lists.newArrayList();
    protected final Class clazz;

    public SpecificationsBuilder(Class clazz) {
        this.clazz = clazz;
    }

    public org.springframework.data.jpa.domain.Specification build() {

        List<org.springframework.data.jpa.domain.Specification> specs = new ArrayList<>();

        for (SearchCriteria param : criteriaList) {
            specs.add(new CustomSpecification<T>(param));
            log.info("Criteria: {}", param);
        }

        org.springframework.data.jpa.domain.Specification result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = result.and(specs.get(i));
        }
        return result;
    }
}
