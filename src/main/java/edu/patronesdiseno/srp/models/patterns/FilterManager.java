package edu.patronesdiseno.srp.models.patterns;

public class FilterManager {
    FilterChain filterChain;

    public FilterManager(PasarelaTarget target){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }
    public void setFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }
}
