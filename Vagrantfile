Vagrant.configure(2) do |config|

  config.vm.box = "centos66-jdk7"
  config.vm.box_check_update = false

  config.rdp.port = 5000

  config.vm.network "private_network", ip: "192.168.33.20"

  config.vm.synced_folder ".", "/work"

  config.vm.provider "virtualbox" do |vb|
    vb.name   = "java-honing"
    vb.gui    = false
    vb.memory = 2024
    vb.cpus   = 2
  end

end

# vim: set ai nu nobk expandtab sw=2 tw=72 ts=4 syntax=ruby :
