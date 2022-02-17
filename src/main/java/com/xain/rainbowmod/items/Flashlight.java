package com.xain.rainbowmod.items;

import com.xain.rainbowmod.RainbowMain;
import com.xain.rainbowmod.tools.LightStorage;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import javax.annotation.Nullable;
import java.util.List;

public class Flashlight extends Item {
    public Flashlight(Properties properties) {
        super(properties);
    }

    private final LightStorage lightStorage = new LightStorage(100); //todo make configurable? with configurable rate too?

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        //todo update the below for the flashlight
        super.appendHoverText(stack, level, list, flag);
        int distance = stack.hasTag() ? stack.getTag().getInt("distance") : 0;
        list.add(new TranslatableComponent("message.flashlight.tooltip", Integer.toString(distance)).withStyle(ChatFormatting.BLUE));
    }
//todo cant figure out how to light area around the player

//    @Override
//    public InteractionResult useOn(UseOnContext context) {
//        var player = context.getPlayer();
//        var level = player.level;
//        if (context.getItemInHand().is(this)) {
//            BlockState state = player.getFeetBlockState();
//            state.setValue(Light);
//            BlockEntity blockEntity = level.getBlockEntity(player.blockPosition());
//        }
//        BlockState state = level.getBlockState(context.getClickedPos());
//
//
//
//
//        return InteractionResult.FAIL;
//    }

}
