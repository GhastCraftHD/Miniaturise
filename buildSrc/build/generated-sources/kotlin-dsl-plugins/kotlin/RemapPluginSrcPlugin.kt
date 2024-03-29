/**
 * Precompiled [remap-plugin-src.gradle.kts][Remap_plugin_src_gradle] script plugin.
 *
 * @see Remap_plugin_src_gradle
 */
public
class RemapPluginSrcPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Remap_plugin_src_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
