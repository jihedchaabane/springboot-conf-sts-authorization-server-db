# springboot-conf-sts-authorization-server-db
-----------------------------------------------------
IN "10.0.0.137" do:
-----------------------------------------------------
sudo firewall-cmd --add-port=8764/tcp --permanent
sudo firewall-cmd --reload

sudo firewall-cmd --list-ports
sudo firewall-cmd --list-all

---Fermer le port 8764 -------------------------------
sudo firewall-cmd --permanent --remove-port=8764/tcp
sudo firewall-cmd --reload

sudo firewall-cmd --list-ports
sudo firewall-cmd --list-all

-----------------------------------------------------

curl -vvv http://10.0.0.137:8764
curl http://10.0.0.137:8764/actuator/health
-----------------------------------------------------
