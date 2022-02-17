package com.xain.rainbowmod.tools;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class LightStorage implements INBTSerializable<Tag> {

    protected int light;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public LightStorage(int capacity)
    {
        this(capacity, capacity, capacity, 0);
    }

    public LightStorage(int capacity, int maxTransfer)
    {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public LightStorage(int capacity, int maxReceive, int maxExtract)
    {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public LightStorage(int capacity, int maxReceive, int maxExtract, int light)
    {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.light = Math.max(0 , Math.min(capacity, light));
    }

    public void setLight(int light){
        this.light = light;
        onLightChanged();
    }

    public void consumeLight(int light) {
        this.light -= light;
        if (this.light < 0) {
            this.light = 0;
        }
        onLightChanged();
    }

    public int receiveLight(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;

        int lightReceived = Math.min(capacity - light, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            light += lightReceived;
        return lightReceived;
    }

    public int extractLight(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;

        int lightExtracted = Math.min(light, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            light -= lightExtracted;
        return lightExtracted;
    }

    public int getLightStored()
    {
        return light;
    }

    public int getMaxLightStored()
    {
        return capacity;
    }

    public boolean canExtract()
    {
        return this.maxExtract > 0;
    }

    public boolean canReceive()
    {
        return this.maxReceive > 0;
    }

    @Override
    public Tag serializeNBT()
    {
        return IntTag.valueOf(this.getLightStored());
    }

    @Override
    public void deserializeNBT(Tag nbt)
    {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.light = intNbt.getAsInt();
    }

    protected void onLightChanged() {

    }
}
