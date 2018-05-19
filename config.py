def can_build(plat):
	return plat in ["android", "iphone"]

def configure(env):
	if env['platform'] == 'android':
		env.android_add_java_dir("android/src")
		env.android_add_to_manifest("android/AndroidManifestChunk.xml")
		env.android_add_dependency("compile 'com.umeng.sdk:common:latest.integration'")
		env.android_add_dependency("compile 'com.umeng.sdk:analytics:latest.integration'")
