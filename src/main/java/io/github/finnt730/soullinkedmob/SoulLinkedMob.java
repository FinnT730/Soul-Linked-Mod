package io.github.finnt730.soullinkedmob;

import com.mojang.logging.LogUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SoulLinkedMob.MODID)
public class SoulLinkedMob {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "soullinkedmob";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SoulLinkedMob() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onMobDies(LivingDeathEvent event) {

            if(event.getEntity() instanceof TamableAnimal e && e.isTame() && e.getOwner() != null) {
//                    ((Player) e.getOwner()).
//                e.getOwner().kill();
                DamageSource source = new IndirectEntityDamageSource("souldeath", e, event.getEntity());
                source.bypassInvul();
                source.bypassArmor();
                source.bypassEnchantments();
                source.bypassMagic();
                e.getOwner().hurt(source, Float.MAX_VALUE);
            }

//            if(event.getEntity() instanceof TamableAnimal e && e.isTame() && e.getOwner() != null) {
//                e.getOwner().kill();
//            }
        }

    }
}
