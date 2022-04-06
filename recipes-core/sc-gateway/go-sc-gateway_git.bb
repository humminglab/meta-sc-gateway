DESCRIPTION = "School Charger Gateway Deamon"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@${GO_IMPORT};branch=main;protocol=ssh"
# SRCREV = "6955095b24119ef018ce4d98f97b1ff394a1f64a"
SRCREV = "${AUTOREV}"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "gitlab.com/humminglab/sc-gateway"
GO_INSTALL = "${GO_IMPORT}"
GO_WORKDIR = "${GO_INSTALL}"

inherit go-mod
