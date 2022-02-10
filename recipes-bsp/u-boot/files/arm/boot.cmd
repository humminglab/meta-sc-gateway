setenv loadaddr ${kernel_addr_r}
setenv bootcmd_resetvars 'setenv kernel_image; setenv bootargs; setenv kernel_image2; setenv bootargs2'
setenv bootcmd_otenv 'run bootcmd_resetvars; load mmc 0:2 $loadaddr /boot/loader/uEnv.txt; env import -t ${loadaddr} ${filesize}'
setenv bootcmd_rollbackenv 'setenv kernel_image ${kernel_image2}; setenv bootargs ${bootargs2}'

setenv bootcmd_args 'setenv bootargs "${bootargs} ${bootargs_fdt} ostree_root=/dev/mmcblk0p2 root=/dev/ram0 rw rootwait rootdelay=2 ramdisk_size=8192 panic=1"'

setenv bootcmd_getroot 'setexpr ostree_root gsub "^.*ostree=([^ ]*).*$" "\\\\1" "${bootargs}";'

setenv bootcmd_fitconf 'run bootcmd_getroot; if test -e mmc 0:2 "${ostree_root}/usr/lib/fit_conf"; then load mmc 0:2 ${loadaddr} "${ostree_root}/usr/lib/fit_conf"; env import -t ${loadaddr} ${filesize}; fi'

setenv bootcmd_load 'load mmc 0:2 ${ramdisk_addr_r} "/boot"${kernel_image}'
setenv bootcmd_run 'bootm "${ramdisk_addr_r}${fit_conf}"'
setenv bootcmd_run 'bootm "${ramdisk_addr_r}"'

setenv bootcmd_create_envfile 'if test ! -e mmc 0:1 uboot.env; then saveenv; fi;'

setenv bootlimit 3

setenv bootcmd 'if test "${rollback}" = "1"; then run altbootcmd; else run bootcmd_create_envfile; run bootcmd_otenv; run bootcmd_args; run bootcmd_fitconf; run bootcmd_load; run bootcmd_run; if ! "${upgrade_available}" = "1"; then setenv upgrade_available 1; saveenv; fi; reset; fi'

setenv bootcmd_set_rollback 'if test ! "${rollback}" = "1"; then setenv rollback 1; setenv upgrade_available 0; saveenv; fi'
setenv altbootcmd 'run bootcmd_create_envfile; run bootcmd_otenv; run bootcmd_set_rollback; if test -n "${kernel_image2}"; then run bootcmd_rollbackenv; fi; run bootcmd_args; run bootcmd_fitconf; run bootcmd_load; run bootcmd_run; reset'

run bootcmd
