package io.github.finnt730.soullinkedmob;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@FunctionalInterface
public interface ILinked {

    boolean isLinkMobPlayer(LivingEntity animal, Player player);

}
