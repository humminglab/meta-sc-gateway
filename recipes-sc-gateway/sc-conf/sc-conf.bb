SUMMARY = "SC-Gateway Configuration"
DESCRIPTION = "SC-Gateway Configuration"
SECTION = "base"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
            file://test.conf \
            "

do_install:append () {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/test.conf ${D}${sysconfdir}/test.conf
}

FILES:${PN} = "${sysconfdir}/test.conf"
