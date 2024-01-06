package me.roundaround.stackables.data;

import me.roundaround.stackables.StackablesMod;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class StackablesDataGenerator implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    StackablesMod.LOGGER.info("Initializing data generator");
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
    pack.addProvider(StackablesTagGenerator::new);
  }
}
