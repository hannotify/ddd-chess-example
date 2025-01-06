package ddd.core.businessrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record AndBusinessRule(BusinessRule... rules) implements BusinessRule {

    @Override
    public List<BusinessRuleViolation> checkRule() {
        List<BusinessRuleViolation> result = new ArrayList<>();

        for (BusinessRule rule : rules) {
            result.addAll(rule.checkRule());
        }

        return result;
    }
}