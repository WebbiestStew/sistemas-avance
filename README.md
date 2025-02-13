Si al ejecutar tu cliente en Java te aparece “Connection refused”, puede deberse a varias razones. Aquí hay algunas posibles causas y soluciones:

1. El servidor no está en ejecución
	•	Causa: Si el servidor no está corriendo en el puerto y dirección especificados, el cliente no podrá conectarse.
	•	Solución: Asegúrate de ejecutar primero el servidor antes de iniciar el cliente.
Ejemplo:
	•	Primero, inicia el servidor:

java Servidor


	•	Luego, inicia el cliente:

java ClienteGestion

2. Puerto incorrecto o en uso
	•	Causa: Puede que el puerto 8080 esté en uso por otra aplicación o no sea el mismo que usa el servidor.
	•	Solución:
	1.	Verifica qué puertos están en uso con:
	•	En Windows: netstat -ano | findstr :8080
	•	En Linux/macOS: lsof -i :8080
	2.	Si el puerto está ocupado, cambia el puerto en el código del servidor y del cliente. Por ejemplo, usa 9090 en lugar de 8080.

3. Dirección IP incorrecta
	•	Causa: Si el servidor está en otra máquina o en una red diferente, 127.0.0.1 (localhost) no funcionará.
	•	Solución:
	•	Si el servidor está en otra computadora dentro de la misma red, usa su dirección IP real en el cliente. Puedes encontrarla con:
	•	Windows: ipconfig → busca IPv4 Address
	•	Linux/macOS: ifconfig o hostname -I
	•	Luego, cambia 127.0.0.1 en el cliente por esa IP.

4. Firewall bloqueando la conexión
	•	Causa: Algunos firewalls bloquean conexiones entrantes y salientes.
	•	Solución:
	•	En Windows, abre el Firewall y permite conexiones en el puerto 8080:
	•	Ve a Panel de Control > Firewall de Windows > Permitir una aplicación
	•	Agrega el programa (java.exe) o habilita el puerto manualmente.
	•	En Linux, prueba deshabilitar temporalmente el firewall:

sudo ufw disable


	•	En macOS, ve a Preferencias del Sistema > Seguridad y Privacidad > Firewall y permite conexiones.

5. Middleware RMI no está corriendo

Si estás usando Java RMI, verifica que el registro RMI esté en ejecución:

rmiregistry 1099 &

Luego, ejecuta el servidor RMI y finalmente el cliente.

Conclusión

Si obtienes “Connection Refused”, sigue estos pasos en orden:
✅ Asegúrate de que el servidor está corriendo
✅ Verifica que el puerto no está ocupado
✅ Usa la IP correcta si el servidor está en otra máquina
✅ Revisa el Firewall y permisos de red
✅ Si usas Java RMI, confirma que rmiregistry está en marcha

Prueba estas soluciones y dime si sigues teniendo problemas 🚀
