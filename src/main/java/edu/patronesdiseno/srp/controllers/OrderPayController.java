package edu.patronesdiseno.srp.controllers;

import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

public interface OrderPayController {
    void create(@NotNull Context context);

    void find(@NotNull Context context);

    void pay(@NotNull Context context);

}
