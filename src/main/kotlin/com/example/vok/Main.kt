package com.example.vok

import com.github.mvysny.vaadinboot.VaadinBoot

/**
 * Run this to launch your app in Embedded Jetty.
 */
fun main(vararg args: String) {
    VaadinBoot().withArgs(args).listenOn("127.0.0.1").run()
}
