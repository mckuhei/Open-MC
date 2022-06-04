package com.SunriseStudio.TeamTCG.openmc.world.block.registery;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BlockGetter{
    String id();
    String behavior();
}
