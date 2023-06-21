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

package com.github.Anon8281.helper.lilypad.plugin;

import com.github.Anon8281.helper.lilypad.extended.LilyPadNetwork;
import com.github.Anon8281.helper.messaging.InstanceData;
import com.github.Anon8281.helper.messaging.Messenger;
import com.github.Anon8281.helper.internal.HelperImplementationPlugin;
import com.github.Anon8281.helper.lilypad.LilyPad;
import com.github.Anon8281.helper.network.Network;
import com.github.Anon8281.helper.network.redirect.PlayerRedirector;
import com.github.Anon8281.helper.network.redirect.RedirectSystem;
import com.github.Anon8281.helper.plugin.ExtendedJavaPlugin;

import lilypad.client.connect.api.Connect;

@HelperImplementationPlugin
public class HelperLilyPadPlugin extends ExtendedJavaPlugin {

    private LilyPad globalLilyPad;

    @Override
    protected void enable() {
        Connect connect = getService(Connect.class);
        this.globalLilyPad = new HelperLilyPad(connect);

        // expose all instances as services.
        provideService(LilyPad.class, this.globalLilyPad);
        provideService(Messenger.class, this.globalLilyPad);
        provideService(InstanceData.class, this.globalLilyPad);
        provideService(PlayerRedirector.class, this.globalLilyPad);

        getLogger().info("Hooked with LilyPad-Connect");
    }

    public Network getNetwork() {
        return Network.obtain(() -> new LilyPadNetwork(this.globalLilyPad));
    }

    public RedirectSystem getRedirectSystem() {
        return RedirectSystem.obtain(() -> RedirectSystem.create(this.globalLilyPad));
    }
}
