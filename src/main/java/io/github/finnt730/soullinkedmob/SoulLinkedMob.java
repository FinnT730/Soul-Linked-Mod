package io.github.finnt730.soullinkedmob;

import com.mojang.logging.LogUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
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
//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.addListener(this::onMobDies);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    public void onMobDies(LivingDeathEvent event) {
        LivingEntity var2 = (LivingEntity) event.getEntity();
        if (var2 instanceof TamableAnimal e) {
            if (e.isTame() && e.getOwner() != null) {
                DamageSource source = new IndirectEntityDamageSource("souldeath", e, event.getEntity());
                source.bypassInvul();
                source.bypassArmor();
                source.bypassMagic();
                e.getOwner().hurt(source, Float.MAX_VALUE);
            }
        }

    }
}
