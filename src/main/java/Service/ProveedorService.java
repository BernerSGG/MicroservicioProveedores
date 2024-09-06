package Service;

import org.springframework.stereotype.Service;

import entity.Proveedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProveedorService {

	private final List<Proveedor> proveedores = new ArrayList<>();
	private final AtomicLong idGenerator = new AtomicLong(1);

	public Proveedor crearProveedor(Proveedor proveedor) {
		proveedor.setId(idGenerator.getAndIncrement());
		proveedores.add(proveedor);
		return proveedor;
	}

	public List<Proveedor> listarProveedores() {
		return proveedores;
	}

	public Optional<Proveedor> obtenerProveedorPorId(Long id) {
		return proveedores.stream().filter(proveedor -> proveedor.getId().equals(id)).findFirst();
	}

	public Proveedor actualizarProveedor(Long id, Proveedor proveedorActualizado) {
		Optional<Proveedor> proveedorOpt = obtenerProveedorPorId(id);
		if (proveedorOpt.isPresent()) {
			Proveedor proveedor = proveedorOpt.get();
			proveedor.setNombre(proveedorActualizado.getNombre());
			proveedor.setDireccion(proveedorActualizado.getDireccion());
			proveedor.setTelefono(proveedorActualizado.getTelefono());
			return proveedor;
		}
		return null;
	}

	public boolean eliminarProveedor(Long id) {
		return proveedores.removeIf(proveedor -> proveedor.getId().equals(id));
	}
}
