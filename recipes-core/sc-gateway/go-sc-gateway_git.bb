DESCRIPTION = "School Charger Gateway Deamon"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@${GO_IMPORT};branch=main;protocol=ssh \
           file://init"
# SRCREV = "e8632952ce4fa42491a7e48fb3586d7eec06069d"
SRCREV = "${AUTOREV}"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "gitlab.com/humminglab/sc-gateway"
GO_INSTALL = "${GO_IMPORT}"
GO_WORKDIR = "${GO_INSTALL}"

inherit go-mod update-rc.d

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
}
