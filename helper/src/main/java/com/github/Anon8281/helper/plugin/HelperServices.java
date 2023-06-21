/*
 * This file is part of helper, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.github.Anon8281.helper.plugin;

import com.github.Anon8281.helper.bossbar.BossBarFactory;
import com.github.Anon8281.helper.bossbar.BukkitBossBarFactory;
import com.github.Anon8281.helper.bossbar.ViaBossBarFactory;
import com.github.Anon8281.helper.hologram.BukkitHologramFactory;
import com.github.Anon8281.helper.hologram.HologramFactory;
import com.github.Anon8281.helper.hologram.individual.IndividualHologramFactory;
import com.github.Anon8281.helper.hologram.individual.PacketIndividualHologramFactory;
import com.github.Anon8281.helper.messaging.bungee.BungeeCord;
import com.github.Anon8281.helper.messaging.bungee.BungeeCordImpl;
import com.github.Anon8281.helper.npc.CitizensNpcFactory;
import com.github.Anon8281.helper.npc.NpcFactory;
import com.github.Anon8281.helper.scoreboard.PacketScoreboardProvider;
import com.github.Anon8281.helper.scoreboard.ScoreboardProvider;
import com.github.Anon8281.helper.signprompt.PacketSignPromptFactory;
import com.github.Anon8281.helper.signprompt.SignPromptFactory;

import org.bukkit.plugin.ServicePriority;

final class HelperServices {
    private HelperServices() {}

    static void setup(ExtendedJavaPlugin plugin) {
        plugin.provideService(HologramFactory.class, new BukkitHologramFactory());
        plugin.provideService(BungeeCord.class, new BungeeCordImpl(plugin));
        if (plugin.isPluginPresent("ProtocolLib")) {
            PacketScoreboardProvider scoreboardProvider = new PacketScoreboardProvider(plugin);
            plugin.provideService(ScoreboardProvider.class, scoreboardProvider);
            plugin.provideService(PacketScoreboardProvider.class, scoreboardProvider);

            SignPromptFactory signPromptFactory = new PacketSignPromptFactory();
            plugin.provideService(SignPromptFactory.class, signPromptFactory);

            try {
                IndividualHologramFactory hologramFactory = new PacketIndividualHologramFactory();
                plugin.provideService(IndividualHologramFactory.class, hologramFactory);
            } catch (Throwable t) {
                // ignore??
            }
        }
        if (plugin.isPluginPresent("Citizens")) {
            CitizensNpcFactory npcManager = plugin.bind(new CitizensNpcFactory());
            plugin.provideService(NpcFactory.class, npcManager);
            plugin.provideService(CitizensNpcFactory.class, npcManager);
        }
        if (plugin.isPluginPresent("ViaVersion")) {
            BossBarFactory bossBarFactory = new ViaBossBarFactory();
            plugin.provideService(BossBarFactory.class, bossBarFactory, ServicePriority.High);
        } else if (classExists("org.bukkit.boss.BossBar")) {
            BossBarFactory bossBarFactory = new BukkitBossBarFactory(plugin.getServer());
            plugin.provideService(BossBarFactory.class, bossBarFactory);
        }
    }

    private static boolean classExists(String clazz) {
        try {
            Class.forName(clazz);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
