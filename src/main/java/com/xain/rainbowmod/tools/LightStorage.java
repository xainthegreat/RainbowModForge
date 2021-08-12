package com.xain.rainbowmod.tools;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class LightStorage implements INBTSerializable<Tag> {

    private int maxLight;
    private int currentLight;
    private boolean extract;
    private boolean receive;

    public LightStorage (int max, boolean in, boolean out) {
        this.setMaxLight(max);
        this.receive = in;
        this.extract = out;
    }

    // only use for light generation, not transfer
    // todo update nameing?
    public void createLight(int input){
        int lightReceived = Math.min(getMaxLight() - getCurrentLight(), input);
        setCurrentLight(getCurrentLight() + lightReceived);
    }

    public int addLight(int input) {
        //todo figure out how to mark the block dirty now?
        if (!canReceive())
            return 0;

        int lightReceived = Math.min(getMaxLight() - getCurrentLight(), input);

        setCurrentLight(getCurrentLight() + lightReceived);

        return lightReceived;
    }

    public void removeLight(int light) {
        //todo figure out how to mark the block dirty now?
        int newLight = getCurrentLight() - light;

        if (newLight < 0) {
            setCurrentLight(0);
        } else {
            setCurrentLight(newLight);
        }
    }

    public int getCurrentLight() {
        return currentLight;
    }

    public void setCurrentLight(int update) {
        this.currentLight = update;
    }

    public int getMaxLight() {
        return maxLight;
    }

    public void setMaxLight(int update) {
        this.maxLight = update;
    }

    public boolean canExtract()
    {
        return extract;
    }

    public boolean canReceive()
    {
        return receive;
    }

    @Override
    public Tag serializeNBT()
    {
        return IntTag.valueOf(getCurrentLight());
    }

    @Override
    public void deserializeNBT(Tag nbt)
    {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        setCurrentLight(intNbt.getAsInt());
    }

    protected void onLightChange() {    }
}
