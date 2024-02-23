package com.keletu.tbmr;

import com.google.common.collect.Lists;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;

public class TBMRLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Lists.newArrayList(
                "mixins.tbmr.json",
                "mixins.whisperweed.json");
    }
}