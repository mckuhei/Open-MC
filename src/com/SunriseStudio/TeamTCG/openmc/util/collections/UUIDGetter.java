package com.SunriseStudio.TeamTCG.openmc.util.collections;

public interface UUIDGetter<T extends UUID<?>> {
    boolean equalsAnother(UUIDGetter<T> uid);

    T getUID();
}
