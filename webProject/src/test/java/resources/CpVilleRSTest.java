package resources;

import static org.mockito.Mockito.mock;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.CpVille;

public class CpVilleRSTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IllegalArgumentException, UriBuilderException, URISyntaxException {
		CpVilleRS cpVilleRS = new CpVilleRS();
		
		CpVille cpVille = new CpVille("75000","Paris");
		cpVille.setId(1L);
		UriInfo uriInfo = mock(UriInfo.class);
		UriBuilder uriBuilder = mock(UriBuilder.class);
		Mockito.when(uriInfo.getRequestUriBuilder()).thenReturn(uriBuilder);
		Mockito.when(uriBuilder.path("1")).thenReturn(uriBuilder);
		Mockito.when(uriBuilder.build()).thenReturn(new URI("http://localhost:8080/bankProjectWeb/rs/cpville/1"));
		
		cpVilleRS.post(cpVille, uriInfo);
	}

}
