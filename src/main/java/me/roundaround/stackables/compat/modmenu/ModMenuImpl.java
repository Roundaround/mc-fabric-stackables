package me.roundaround.stackables.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.roundaround.roundalib.client.gui.screen.ConfigScreen;
import me.roundaround.stackables.StackablesMod;
import me.roundaround.stackables.config.StackablesConfig;

public class ModMenuImpl implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> {
      StackablesConfig config = StackablesConfig.getInstance();
      if (!config.isReady()) {
        return null;
      }
      return new ConfigScreen(screen, StackablesMod.MOD_ID, config);
    };
  }
}
