package me.roundaround.stackables.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.roundaround.roundalib.config.gui.screen.ConfigScreen;
import me.roundaround.stackables.StackablesMod;

public class ModMenuImpl implements ModMenuApi {
  @Override
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return (screen) -> new ConfigScreen(screen, StackablesMod.CONFIG);
  }
}
