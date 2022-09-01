package dev.brella.kornea.gradle

import org.gradle.api.Project

@KorneaDsl
public inline fun Project.ktorModules(block: @KorneaDsl KtorModuleDependencies.() -> Unit) =
    KtorModuleDependencies(this).block()

@KorneaDsl
public inline fun Project.ktorClientModules(block: @KorneaDsl KtorModuleDependencies.Client.() -> Unit) =
    KtorModuleDependencies.Client(this).block()

@KorneaDsl
public inline fun Project.ktorServerModules(block: @KorneaDsl KtorModuleDependencies.Server.() -> Unit) =
    KtorModuleDependencies.Server(this).block()

@KorneaDsl
public inline fun Project.kotlinxSerialisationModules(block: @KorneaDsl KotlinxSerialisationModuleDependencies.() -> Unit) =
    KotlinxSerialisationModuleDependencies(this).block()

@KorneaDsl
public inline fun Project.kotlinxCoroutinesModules(block: @KorneaDsl KotlinxCoroutinesModuleDependencies.() -> Unit) =
    KotlinxCoroutinesModuleDependencies(this).block()

@KorneaDsl
public inline fun Project.customModules(group: String, moduleName: String, block: @KorneaDsl CustomModuleDependencies.() -> Unit) =
    CustomModuleDependencies(this, group, null, moduleName).block()

@KorneaDsl
public inline fun Project.customModules(group: String, baseModule: String?, moduleName: String, block: @KorneaDsl CustomModuleDependencies.() -> Unit) =
    CustomModuleDependencies(this, group, baseModule, moduleName).block()

public abstract class GroupedModuleDependencies(
    val project: Project,
    group: String,
    baseModule: String?,
    val moduleName: String,
) {
    public val baseSpec: String by lazy {
        buildString {
            append(group)
            append(':')
            baseModule?.let(this::append)
        }
    }

    public inline fun of(module: String, defaultVersion: String? = null) =
        project.versioned("${baseSpec}${module}", moduleName, defaultVersion)
}

public class CustomModuleDependencies(project: Project, group: String, baseModule: String?, moduleName: String) :
    GroupedModuleDependencies(project, group, baseModule, moduleName)

public class KtorModuleDependencies(project: Project) :
    GroupedModuleDependencies(project, "io.ktor", "ktor-", KTOR_MODULE_NAME) {
    public class Client(project: Project) :
        GroupedModuleDependencies(project, "io.ktor", "ktor-client-", KTOR_MODULE_NAME) {
        public inline fun android(defaultVersion: String? = null) =
            of("android", defaultVersion)

        public inline fun apache(defaultVersion: String? = null) =
            of("apache", defaultVersion)

        public inline fun cio(defaultVersion: String? = null) =
            of("cio", defaultVersion)

        public inline fun core(defaultVersion: String? = null) =
            of("core", defaultVersion)

        public inline fun curl(defaultVersion: String? = null) =
            of("curl", defaultVersion)

        public inline fun darwin(defaultVersion: String? = null) =
            of("darwin", defaultVersion)

        public inline fun java(defaultVersion: String? = null) =
            of("java", defaultVersion)

        public inline fun jetty(defaultVersion: String? = null) =
            of("jetty", defaultVersion)

        public inline fun mock(defaultVersion: String? = null) =
            of("mock", defaultVersion)

        public inline fun okhttp(defaultVersion: String? = null) =
            of("okhttp", defaultVersion)

        public inline fun auth(defaultVersion: String? = null) =
            of("auth", defaultVersion)

        public inline fun contentNegotiation(defaultVersion: String? = null) =
            of("content-negotiation", defaultVersion)

        public inline fun encoding(defaultVersion: String? = null) =
            of("encoding", defaultVersion)

        public inline fun json(defaultVersion: String? = null) =
            of("json", defaultVersion)

        public inline fun gson(defaultVersion: String? = null) =
            of("gson", defaultVersion)

        public inline fun jackson(defaultVersion: String? = null) =
            of("jackson", defaultVersion)

        public inline fun serialisation(defaultVersion: String? = null) =
            of("serialization", defaultVersion)

        public inline fun logging(defaultVersion: String? = null) =
            of("logging", defaultVersion)

        public inline fun resources(defaultVersion: String? = null) =
            of("resources", defaultVersion)
    }

    public class Server(project: Project) :
        GroupedModuleDependencies(project, "io.ktor", "ktor-server-", KTOR_MODULE_NAME) {
        public inline fun cio(defaultVersion: String? = null) =
            of("cio", defaultVersion)

        public inline fun core(defaultVersion: String? = null) =
            of("core", defaultVersion)

        public inline fun hostCommon(defaultVersion: String? = null) =
            of("host-common", defaultVersion)

        public inline fun jetty(defaultVersion: String? = null) =
            of("jetty", defaultVersion)

        public inline fun netty(defaultVersion: String? = null) =
            of("netty", defaultVersion)

        public inline fun auth(defaultVersion: String? = null) =
            of("auth", defaultVersion)

        public inline fun authJwt(defaultVersion: String? = null) =
            of("auth-jwt", defaultVersion)

        public inline fun authLdap(defaultVersion: String? = null) =
            of("auth-ldap", defaultVersion)

        public inline fun autoHeadResponse(defaultVersion: String? = null) =
            of("auto-head-response", defaultVersion)

        public inline fun cachingHeaders(defaultVersion: String? = null) =
            of("caching-headers", defaultVersion)

        public inline fun callId(defaultVersion: String? = null) =
            of("call-id", defaultVersion)

        public inline fun callLogging(defaultVersion: String? = null) =
            of("call-logging", defaultVersion)

        public inline fun compression(defaultVersion: String? = null) =
            of("compression", defaultVersion)

        public inline fun conditionalHeaders(defaultVersion: String? = null) =
            of("conditional-headers", defaultVersion)

        public inline fun contentNegotiation(defaultVersion: String? = null) =
            of("content-negotiation", defaultVersion)

        public inline fun cors(defaultVersion: String? = null) =
            of("cors", defaultVersion)

        public inline fun dataConversion(defaultVersion: String? = null) =
            of("data-conversion", defaultVersion)

        public inline fun defaultHeaders(defaultVersion: String? = null) =
            of("default-headers", defaultVersion)

        public inline fun doubleReceive(defaultVersion: String? = null) =
            of("double-receive", defaultVersion)

        public inline fun forwardedHeader(defaultVersion: String? = null) =
            of("forwarded-header", defaultVersion)

        public inline fun freemarker(defaultVersion: String? = null) =
            of("freemarker", defaultVersion)

        public inline fun hsts(defaultVersion: String? = null) =
            of("hsts", defaultVersion)

        public inline fun htmlBuilder(defaultVersion: String? = null) =
            of("html-builder", defaultVersion)

        public inline fun httpRedirect(defaultVersion: String? = null) =
            of("http-redirect", defaultVersion)

        public inline fun jte(defaultVersion: String? = null) =
            of("jte", defaultVersion)

        public inline fun locations(defaultVersion: String? = null) =
            of("locations", defaultVersion)

        public inline fun methodOverride(defaultVersion: String? = null) =
            of("method-override", defaultVersion)

        public inline fun metrics(defaultVersion: String? = null) =
            of("metrics", defaultVersion)

        public inline fun metricsMicrometer(defaultVersion: String? = null) =
            of("metrics-micrometer", defaultVersion)

        public inline fun mustache(defaultVersion: String? = null) =
            of("mustache", defaultVersion)

        public inline fun partialContent(defaultVersion: String? = null) =
            of("partial-content", defaultVersion)

        public inline fun pebble(defaultVersion: String? = null) =
            of("pebble", defaultVersion)

        public inline fun resources(defaultVersion: String? = null) =
            of("resources", defaultVersion)

        public inline fun sessions(defaultVersion: String? = null) =
            of("sessions", defaultVersion)

        public inline fun statusPages(defaultVersion: String? = null) =
            of("status-pages", defaultVersion)

        public inline fun thymeleaf(defaultVersion: String? = null) =
            of("thymeleaf", defaultVersion)

        public inline fun velocity(defaultVersion: String? = null) =
            of("velocity", defaultVersion)

        public inline fun webjars(defaultVersion: String? = null) =
            of("webjars", defaultVersion)

        public inline fun websockets(defaultVersion: String? = null) =
            of("websockets", defaultVersion)

        public inline fun servlet(defaultVersion: String? = null) =
            of("servlet", defaultVersion)

        public inline fun tomcat(defaultVersion: String? = null) =
            of("tomcat", defaultVersion)
    }

    @KorneaDsl
    public inline fun clientModules(block: @KorneaDsl Client.() -> Unit) =
        Client(project).block()

    @KorneaDsl
    public inline fun serverModules(block: @KorneaDsl Server.() -> Unit) =
        Server(project).block()

    public inline fun http(defaultVersion: String? = null) =
        of("http", defaultVersion)

    public inline fun httpCio(defaultVersion: String? = null) =
        of("http-cio", defaultVersion)

    public inline fun io(defaultVersion: String? = null) =
        of("io", defaultVersion)

    public inline fun network(defaultVersion: String? = null) =
        of("network", defaultVersion)

    public inline fun networkTls(defaultVersion: String? = null) =
        of("network-tls", defaultVersion)

    public inline fun networkTlsCertificates(defaultVersion: String? = null) =
        of("network-tls-certificates", defaultVersion)

    public inline fun events(defaultVersion: String? = null) =
        of("events", defaultVersion)

    public inline fun resources(defaultVersion: String? = null) =
        of("resources", defaultVersion)

    public inline fun serialization(defaultVersion: String? = null) =
        of("serialization", defaultVersion)

    public inline fun serializationGson(defaultVersion: String? = null) =
        of("serialization-gson", defaultVersion)

    public inline fun serialisationJackson(defaultVersion: String? = null) =
        of("serialization-jackson", defaultVersion)

    public inline fun serialisationKotlinx(defaultVersion: String? = null) =
        of("serialization-kotlinx", defaultVersion)

    public inline fun serialisationKotlinxCbor(defaultVersion: String? = null) =
        of("serializationKotlinx-cbor", defaultVersion)

    public inline fun serialisationKotlinxJson(defaultVersion: String? = null) =
        of("serialization-kotlinx-json", defaultVersion)

    public inline fun serialisationKotlinxXml(defaultVersion: String? = null) =
        of("serialization-kotlinx-xml", defaultVersion)

    public inline fun websocketSerialisation(defaultVersion: String? = null) =
        of("websocket-serialization", defaultVersion)

    public inline fun websockets(defaultVersion: String? = null) =
        of("websockets", defaultVersion)

    public inline fun utils(defaultVersion: String? = null) =
        of("utils", defaultVersion)
}

public class KotlinxSerialisationModuleDependencies(project: Project) : GroupedModuleDependencies(
    project,
    "org.jetbrains.kotlinx",
    "kotlinx-serialization-",
    KOTLINX_SERIALISATION_MODULE_NAME
) {
    public inline fun core(defaultVersion: String? = null) =
        of("core", defaultVersion)

    public inline fun cbor(defaultVersion: String? = null) =
        of("cbor", defaultVersion)

    public inline fun hocon(defaultVersion: String? = null) =
        of("hocon", defaultVersion)

    public inline fun json(defaultVersion: String? = null) =
        of("json", defaultVersion)

    public inline fun properties(defaultVersion: String? = null) =
        of("properties", defaultVersion)

    public inline fun protobuf(defaultVersion: String? = null) =
        of("protobuf", defaultVersion)
}

public class KotlinxCoroutinesModuleDependencies(project: Project) :
    GroupedModuleDependencies(project, "org.jetbrains.kotlinx", "kotlinx-coroutines-", KOTLINX_COROUTINES_MODULE_NAME) {
    public inline fun core(defaultVersion: String? = null) =
        of("core", defaultVersion)

    public inline fun test(defaultVersion: String? = null) =
        of("test", defaultVersion)

    public inline fun debug(defaultVersion: String? = null) =
        of("debug", defaultVersion)

    public inline fun reactive(defaultVersion: String? = null) =
        of("reactive", defaultVersion)

    public inline fun reactor(defaultVersion: String? = null) =
        of("reactor", defaultVersion)

    public inline fun rx2(defaultVersion: String? = null) =
        of("rx2", defaultVersion)

    public inline fun rx3(defaultVersion: String? = null) =
        of("rx3", defaultVersion)

    public inline fun android(defaultVersion: String? = null) =
        of("android", defaultVersion)

    public inline fun javafx(defaultVersion: String? = null) =
        of("javafx", defaultVersion)

    public inline fun swing(defaultVersion: String? = null) =
        of("swing", defaultVersion)

    public inline fun jdk8(defaultVersion: String? = null) =
        of("jdk8", defaultVersion)

    public inline fun guava(defaultVersion: String? = null) =
        of("guava", defaultVersion)

    public inline fun slf4J(defaultVersion: String? = null) =
        of("slf4j", defaultVersion)

    public inline fun playServices(defaultVersion: String? = null) =
        of("play-services", defaultVersion)
}