# For mediatek MT7601U
LICENSE:${PN}-mt76x0 = "Firmware-ralink_a_mediatek_company_firmware"
LICENSE:${PN}-mt76x0-license = "Firmware-ralink_a_mediatek_company_firmware"

FILES:${PN}-mt76x0-license = "${nonarch_base_libdir}/firmware/LICENCE.ralink_a_mediatek_company_firmware"
FILES:${PN}-mt76x0 = " \
  ${nonarch_base_libdir}/firmware/mediatek/mt7610e.bin \
  ${nonarch_base_libdir}/firmware/mediatek/mt7610u.bin \
"

RDEPENDS:${PN}-mt76x0 += "${PN}-mt76x0-license"

PACKAGES =+ "${PN}-mt76x0-license ${PN}-mt76x0"
