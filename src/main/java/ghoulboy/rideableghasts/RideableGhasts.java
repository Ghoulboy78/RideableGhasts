package ghoulboy.rideableghasts;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Map;
import java.util.HashMap;

public class RideableGhasts implements CarpetExtension, ModInitializer
{
    @Override
    public String version()
    {
        return "rideableghasts";
    }

    @Override
    public void onInitialize()
    {
        CarpetServer.manageExtension(new RideableGhasts());
    }

    @Override
    public void onGameStarted()
    {
        // let's /carpet handle our few simple settings
        //CarpetServer.settingsManager.parseSettingsClass(CarpetExtraSettings.class);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess)
    {

    }

    @Override
    public Map<String, String> canHasTranslations(String lang)
    {
        return new HashMap<>();
        //return CarpetExtraTranslations.getTranslationFromResourcePath(lang);
    }
}
