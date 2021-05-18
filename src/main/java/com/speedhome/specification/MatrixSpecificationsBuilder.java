package com.speedhome.specification;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.speedhome.specification.CustomSpecificationConstant.*;

@Slf4j
public class MatrixSpecificationsBuilder<T> extends SpecificationsBuilder<T> {

    private final Map<String, List<Object>> matrixVars;

    public MatrixSpecificationsBuilder(Map<String, List<Object>> matrixVars, Class clazz) {
        super(clazz);

        this.matrixVars = Maps.newHashMap(matrixVars);
    }

    public Specification<T> build() {

        if (!matrixVars.containsKey(DELETED)) {

            matrixVars.put(DELETED, Lists.newArrayList(false));
        }

        Set<String> keys = Sets.newHashSet(matrixVars.keySet());
        for (String key : keys) {

            if (Lists.newArrayList(IgnoreMatrixParam.values())
                    .parallelStream().map(Enum::name)
                    .collect(Collectors.toList()).contains(key)) {
                continue;
            }

            List<Object> values = matrixVars.get(key);
            log.debug("key: {}, values: {}", key, values);

            if (CollectionUtils.isEmpty(values)) {
                log.debug("Ignoring key: {} due to no values", key);
                continue;
            }

            if (StringUtils.endsWithIgnoreCase(key, SUFFIX_GT)) {
                criteriaList.add(new SearchCriteria(StringUtils.removeEndIgnoreCase(key, SUFFIX_GT), OP_GT, values.get(0)));

            } else if (StringUtils.endsWithIgnoreCase(key, SUFFIX_LT)) {
                criteriaList.add(new SearchCriteria(StringUtils.removeEndIgnoreCase(key, SUFFIX_LT), OP_LT, values.get(0)));

            } else if (StringUtils.endsWithIgnoreCase(key, SUFFIX_LIKE)) {
                criteriaList.add(new SearchCriteria(StringUtils.removeEndIgnoreCase(key, SUFFIX_LIKE), OP_LIKE, values.get(0)));

            } else if (StringUtils.endsWithIgnoreCase(key, SUFFIX_GTEQ)) {
                criteriaList.add(new SearchCriteria(StringUtils.removeEndIgnoreCase(key, SUFFIX_GTEQ), OP_GTEQ, values.get(0)));

            } else if (StringUtils.endsWithIgnoreCase(key, SUFFIX_LTEQ)) {
                criteriaList.add(new SearchCriteria(StringUtils.removeEndIgnoreCase(key, SUFFIX_LTEQ), OP_LTEQ, values.get(0)));

            } else if (StringUtils.endsWithIgnoreCase(key, SUFFIX_LTEQ)) {
                criteriaList.add(new SearchCriteria(StringUtils.removeEndIgnoreCase(key, SUFFIX_LTEQ), OP_LTEQ, values.get(0)));

            } else if (StringUtils.endsWithIgnoreCase(key, SUFFIX_NOTEQ)) {

                key = StringUtils.removeEndIgnoreCase(key, SUFFIX_NOTEQ);

                if (values.size() == 1) {

                    Object value = values.get(0);

                    criteriaList.add(new SearchCriteria(key, OP_NOT_EQUAL, value));

                } else {


                    criteriaList.add(new SearchCriteria(key, OP_NOT_IN, values));
                }
            } else {

                if (values.size() == 1) {

                    Object value = values.get(0);

                    criteriaList.add(new SearchCriteria(key, OP_EQUAL, value));

                } else {

                    criteriaList.add(new SearchCriteria(key, OP_IN, values));
                }
            }
        }
//        }

        return super.build();
    }
}
