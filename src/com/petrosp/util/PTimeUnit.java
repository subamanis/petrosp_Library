package com.petrosp.util;

public enum PTimeUnit
{
    Nanoseconds(1000000d, "ns"),
    Microseconds(1000d, "μs"),
    Milliseconds(1d, "ms"),
    Seconds(1d / 1000d, "secs");

    double conversionFactorToMs;
    String unitName;

    PTimeUnit(double conversionFactorToMs, String unitName)
    {
        this.conversionFactorToMs = conversionFactorToMs;
        this.unitName = unitName;
    }

    public double convert(long value, PTimeUnit unit)
    {
        double factor = unit.conversionFactorToMs / this.conversionFactorToMs;
        return value * factor;
    }

    public double convert(double value, PTimeUnit unit)
    {
        double factor = unit.conversionFactorToMs / this.conversionFactorToMs;
        return value * factor;
    }
}
