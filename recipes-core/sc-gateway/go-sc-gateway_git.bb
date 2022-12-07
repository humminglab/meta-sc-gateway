DESCRIPTION = "School Charger Gateway Deamon"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@${GO_IMPORT};branch=main;protocol=ssh \
		   file://sc-gateway.service \
           file://init"
SRCREV = "2a9309ecd4b6f3a44e3a42f50efc4868aad994da"
# SRCREV = "${AUTOREV}"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "gitlab.com/humminglab/sc-gateway"
GO_INSTALL = "${GO_IMPORT}"
GO_WORKDIR = "${GO_INSTALL}"

inherit go-mod update-rc.d systemd

SYSTEMD_SERVICE:${PN} = "sc-gateway.service"

INITSCRIPT_NAME = "sc-gateway"
INITSCRIPT_PARAMS = "defaults 21"

do_install:append() {
    install -d ${D}${sysconfdir} \
		${D}${sysconfdir}/init.d
    
    sed -e 's,/etc,${sysconfdir},g' \
		-e 's,/usr/sbin,${sbindir},g' \
		-e 's,/var,${localstatedir},g' \
		-e 's,/usr/bin,${bindir},g' \
		-e 's,/usr,${prefix},g' ${WORKDIR}/init > ${D}${sysconfdir}/init.d/sc-gateway
	chmod 755 ${D}${sysconfdir}/init.d/sc-gateway

    # For systemd
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/sc-gateway.service ${D}${systemd_unitdir}/system
        sed -i -e 's,@BASE_BINDIR@,${base_bindir},g' \
            -e 's,@BINDIR@,${bindir},g' \
            -e 's,@STATEDIR@,${localstatedir},g' \
            -e 's,@SYSCONFDIR@,${sysconfdir},g' \
            ${D}${systemd_unitdir}/system/sc-gateway.service

        # install -d ${D}${sysconfdir}/tmpfiles.d/
        # install -m 0644 ${WORKDIR}/radiusd-volatiles.conf ${D}${sysconfdir}/tmpfiles.d/radiusd.conf
    fi
}
