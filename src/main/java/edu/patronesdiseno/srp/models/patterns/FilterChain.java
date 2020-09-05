package edu.patronesdiseno.srp.models.patterns;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {

    private List<Filter> filters = new ArrayList<Filter>();
    private PasarelaTarget target;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void execute(String request){
        for (Filter filter : filters) {
            request = filter.execute(request);
        }
        target.execute(request);
    }

    public void setTarget(PasarelaTarget target){
        this.target = target;
    }
}
