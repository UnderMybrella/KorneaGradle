import dev.brella.kornea.gradle.GroupedModuleDependencies
import dev.brella.kornea.gradle.KTOR_MODULE_NAME
import dev.brella.kornea.gradle.KorneaDsl
import org.gradle.api.Project

public class KtorModuleDependencies(project: Project) : 
	GroupedModuleDependencies(project, "io.ktor", "ktor-", KTOR_MODULE_NAME) {

	public class Client(project: Project) : 
		GroupedModuleDependencies(project, "io.ktor", "ktor-client-", KTOR_MODULE_NAME) {

		public fun android(defaultVersion: String? = null) = 
			of("android", defaultVersion)

		public fun apache(defaultVersion: String? = null) = 
			of("apache", defaultVersion)

		public fun auth(defaultVersion: String? = null) = 
			of("auth", defaultVersion)

		public fun cio(defaultVersion: String? = null) = 
			of("cio", defaultVersion)

		public fun contentNegotiation(defaultVersion: String? = null) = 
			of("content-negotiation", defaultVersion)

		public fun contentNegotiationTests(defaultVersion: String? = null) = 
			of("content-negotiation-tests", defaultVersion)

		public fun core(defaultVersion: String? = null) = 
			of("core", defaultVersion)

		public fun curl(defaultVersion: String? = null) = 
			of("curl", defaultVersion)

		public fun darwin(defaultVersion: String? = null) = 
			of("darwin", defaultVersion)

		public fun darwinLegacy(defaultVersion: String? = null) = 
			of("darwin-legacy", defaultVersion)

		public fun encoding(defaultVersion: String? = null) = 
			of("encoding", defaultVersion)

		public fun gson(defaultVersion: String? = null) = 
			of("gson", defaultVersion)

		public fun ios(defaultVersion: String? = null) = 
			of("ios", defaultVersion)

		public fun jackson(defaultVersion: String? = null) = 
			of("jackson", defaultVersion)

		public fun java(defaultVersion: String? = null) = 
			of("java", defaultVersion)

		public fun jetty(defaultVersion: String? = null) = 
			of("jetty", defaultVersion)

		public fun js(defaultVersion: String? = null) = 
			of("js", defaultVersion)

		public fun json(defaultVersion: String? = null) = 
			of("json", defaultVersion)

		public fun jsonTests(defaultVersion: String? = null) = 
			of("json-tests", defaultVersion)

		public fun logging(defaultVersion: String? = null) = 
			of("logging", defaultVersion)

		public fun mock(defaultVersion: String? = null) = 
			of("mock", defaultVersion)

		public fun okhttp(defaultVersion: String? = null) = 
			of("okhttp", defaultVersion)

		public fun plugins(defaultVersion: String? = null) = 
			of("plugins", defaultVersion)

		public fun resources(defaultVersion: String? = null) = 
			of("resources", defaultVersion)

		public fun serialisation(defaultVersion: String? = null) = 
			of("serialization", defaultVersion)

		public fun tests(defaultVersion: String? = null) = 
			of("tests", defaultVersion)

		public fun websockets(defaultVersion: String? = null) = 
			of("websockets", defaultVersion)

		public fun winhttp(defaultVersion: String? = null) = 
			of("winhttp", defaultVersion)

	}

	public class Server(project: Project) : 
		GroupedModuleDependencies(project, "io.ktor", "ktor-server-", KTOR_MODULE_NAME) {

		public fun auth(defaultVersion: String? = null) = 
			of("auth", defaultVersion)

		public fun authJwt(defaultVersion: String? = null) = 
			of("auth-jwt", defaultVersion)

		public fun authLdap(defaultVersion: String? = null) = 
			of("auth-ldap", defaultVersion)

		public fun autoHeadResponse(defaultVersion: String? = null) = 
			of("auto-head-response", defaultVersion)

		public fun cachingHeaders(defaultVersion: String? = null) = 
			of("caching-headers", defaultVersion)

		public fun callID(defaultVersion: String? = null) = 
			of("call-id", defaultVersion)

		public fun callLogging(defaultVersion: String? = null) = 
			of("call-logging", defaultVersion)

		public fun cio(defaultVersion: String? = null) = 
			of("cio", defaultVersion)

		public fun compression(defaultVersion: String? = null) = 
			of("compression", defaultVersion)

		public fun conditionalHeaders(defaultVersion: String? = null) = 
			of("conditional-headers", defaultVersion)

		public fun configYaml(defaultVersion: String? = null) = 
			of("config-yaml", defaultVersion)

		public fun contentNegotiation(defaultVersion: String? = null) = 
			of("content-negotiation", defaultVersion)

		public fun core(defaultVersion: String? = null) = 
			of("core", defaultVersion)

		public fun cors(defaultVersion: String? = null) = 
			of("cors", defaultVersion)

		public fun dataConversion(defaultVersion: String? = null) = 
			of("data-conversion", defaultVersion)

		public fun defaultHeaders(defaultVersion: String? = null) = 
			of("default-headers", defaultVersion)

		public fun doubleReceive(defaultVersion: String? = null) = 
			of("double-receive", defaultVersion)

		public fun forwardedHeader(defaultVersion: String? = null) = 
			of("forwarded-header", defaultVersion)

		public fun freemarker(defaultVersion: String? = null) = 
			of("freemarker", defaultVersion)

		public fun host(defaultVersion: String? = null) = 
			of("host", defaultVersion)

		public fun hostCommon(defaultVersion: String? = null) = 
			of("host-common", defaultVersion)

		public fun hsts(defaultVersion: String? = null) = 
			of("hsts", defaultVersion)

		public fun htmlBuilder(defaultVersion: String? = null) = 
			of("html-builder", defaultVersion)

		public fun httpRedirect(defaultVersion: String? = null) = 
			of("http-redirect", defaultVersion)

		public fun jetty(defaultVersion: String? = null) = 
			of("jetty", defaultVersion)

		public fun jettyTestHttp2(defaultVersion: String? = null) = 
			of("jetty-test-http2", defaultVersion)

		public fun jte(defaultVersion: String? = null) = 
			of("jte", defaultVersion)

		public fun locations(defaultVersion: String? = null) = 
			of("locations", defaultVersion)

		public fun methodOverride(defaultVersion: String? = null) = 
			of("method-override", defaultVersion)

		public fun metrics(defaultVersion: String? = null) = 
			of("metrics", defaultVersion)

		public fun metricsMicrometer(defaultVersion: String? = null) = 
			of("metrics-micrometer", defaultVersion)

		public fun mustache(defaultVersion: String? = null) = 
			of("mustache", defaultVersion)

		public fun netty(defaultVersion: String? = null) = 
			of("netty", defaultVersion)

		public fun openapi(defaultVersion: String? = null) = 
			of("openapi", defaultVersion)

		public fun partialContent(defaultVersion: String? = null) = 
			of("partial-content", defaultVersion)

		public fun pebble(defaultVersion: String? = null) = 
			of("pebble", defaultVersion)

		public fun plugins(defaultVersion: String? = null) = 
			of("plugins", defaultVersion)

		public fun rateLimit(defaultVersion: String? = null) = 
			of("rate-limit", defaultVersion)

		public fun requestValidation(defaultVersion: String? = null) = 
			of("request-validation", defaultVersion)

		public fun resources(defaultVersion: String? = null) = 
			of("resources", defaultVersion)

		public fun servlet(defaultVersion: String? = null) = 
			of("servlet", defaultVersion)

		public fun sessions(defaultVersion: String? = null) = 
			of("sessions", defaultVersion)

		public fun statusPages(defaultVersion: String? = null) = 
			of("status-pages", defaultVersion)

		public fun swagger(defaultVersion: String? = null) = 
			of("swagger", defaultVersion)

		public fun testHost(defaultVersion: String? = null) = 
			of("test-host", defaultVersion)

		public fun testSuites(defaultVersion: String? = null) = 
			of("test-suites", defaultVersion)

		public fun tests(defaultVersion: String? = null) = 
			of("tests", defaultVersion)

		public fun thymeleaf(defaultVersion: String? = null) = 
			of("thymeleaf", defaultVersion)

		public fun tomcat(defaultVersion: String? = null) = 
			of("tomcat", defaultVersion)

		public fun velocity(defaultVersion: String? = null) = 
			of("velocity", defaultVersion)

		public fun webjars(defaultVersion: String? = null) = 
			of("webjars", defaultVersion)

		public fun websockets(defaultVersion: String? = null) = 
			of("websockets", defaultVersion)

	}

	public fun bom(defaultVersion: String? = null) = 
		of("bom", defaultVersion)

	public fun client(defaultVersion: String? = null) = 
		of("client", defaultVersion)

	public fun events(defaultVersion: String? = null) = 
		of("events", defaultVersion)

	public fun http(defaultVersion: String? = null) = 
		of("http", defaultVersion)

	public fun httpCIO(defaultVersion: String? = null) = 
		of("http-cio", defaultVersion)

	public fun io(defaultVersion: String? = null) = 
		of("io", defaultVersion)

	public fun network(defaultVersion: String? = null) = 
		of("network", defaultVersion)

	public fun networkTLS(defaultVersion: String? = null) = 
		of("network-tls", defaultVersion)

	public fun networkTLSCertificates(defaultVersion: String? = null) = 
		of("network-tls-certificates", defaultVersion)

	public fun resources(defaultVersion: String? = null) = 
		of("resources", defaultVersion)

	public fun serialisation(defaultVersion: String? = null) = 
		of("serialization", defaultVersion)

	public fun serialisationGson(defaultVersion: String? = null) = 
		of("serialization-gson", defaultVersion)

	public fun serialisationJackson(defaultVersion: String? = null) = 
		of("serialization-jackson", defaultVersion)

	public fun serialisationKotlinx(defaultVersion: String? = null) = 
		of("serialization-kotlinx", defaultVersion)

	public fun serialisationKotlinxCbor(defaultVersion: String? = null) = 
		of("serialization-kotlinx-cbor", defaultVersion)

	public fun serialisationKotlinxJson(defaultVersion: String? = null) = 
		of("serialization-kotlinx-json", defaultVersion)

	public fun serialisationKotlinxProtobuf(defaultVersion: String? = null) = 
		of("serialization-kotlinx-protobuf", defaultVersion)

	public fun serialisationKotlinxTests(defaultVersion: String? = null) = 
		of("serialization-kotlinx-tests", defaultVersion)

	public fun serialisationKotlinxXml(defaultVersion: String? = null) = 
		of("serialization-kotlinx-xml", defaultVersion)

	public fun server(defaultVersion: String? = null) = 
		of("server", defaultVersion)

	public fun shared(defaultVersion: String? = null) = 
		of("shared", defaultVersion)

	public fun testDispatcher(defaultVersion: String? = null) = 
		of("test-dispatcher", defaultVersion)

	public fun utils(defaultVersion: String? = null) = 
		of("utils", defaultVersion)

	public fun websocketSerialisation(defaultVersion: String? = null) = 
		of("websocket-serialization", defaultVersion)

	public fun websockets(defaultVersion: String? = null) = 
		of("websockets", defaultVersion)

	@KorneaDsl
	public inline fun clientModules(block: @KorneaDsl Client.() -> Unit) =
		Client(project).block()

	@KorneaDsl
	public inline fun serverModules(block: @KorneaDsl Server.() -> Unit) =
		Server(project).block()
}

@KorneaDsl
public inline fun Project.ktorModules(block: @KorneaDsl KtorModuleDependencies.() -> Unit) = 
	KtorModuleDependencies(this).block()

@KorneaDsl
public inline fun Project.ktorClientModules(block: @KorneaDsl KtorModuleDependencies.Client.() -> Unit) = 
	KtorModuleDependencies.Client(this).block()

@KorneaDsl
public inline fun Project.ktorServerModules(block: @KorneaDsl KtorModuleDependencies.Server.() -> Unit) = 
	KtorModuleDependencies.Server(this).block()
