SUMMARY = "SC-Gateway Configuration"
DESCRIPTION = "SC-Gateway Configuration"
SECTION = "base"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

SRC_URI = " \
            file://test.conf \
            file://wpa_supplicant-wlan0.conf \
            file://wlan.network \
            "

do_install:append () {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/test.conf ${D}${sysconfdir}/test.conf

    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -s /usr/lib/systemd/system/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service

    install -d ${D}${sysconfdir}/wpa_supplicant
    install -m 0644 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf

    install -d ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/wlan.network ${D}${sysconfdir}/systemd/network/25-wlan.network
}

FILES:${PN} = "${sysconfdir}/test.conf \
    ${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service \
    ${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf \
    ${sysconfdir}/systemd/network/25-wlan.network \
"
