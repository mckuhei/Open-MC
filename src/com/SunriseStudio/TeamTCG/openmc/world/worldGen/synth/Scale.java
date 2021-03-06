package com.SunriseStudio.TeamTCG.openmc.world.worldGen.synth;

public class Scale
extends Synth {
    private Synth synth;
    private double xScale;
    private double yScale;

    public Scale(Synth synth, double xScale, double yScale) {
        this.synth = synth;
        this.xScale = 1.0 / xScale;
        this.yScale = 1.0 / yScale;
    }

    @Override
    public double getValue(double x, double y) {
        return this.synth.getValue(x * this.xScale, y * this.yScale);
    }
}
